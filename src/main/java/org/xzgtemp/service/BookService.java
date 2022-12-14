package org.xzgtemp.service;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.xzgtemp.entity.Book;
import org.xzgtemp.entity.User;

@Component
public class BookService {


	@Autowired
	JdbcTemplate jdbctemplate;

	RowMapper<Book> bookRowMapper = new BeanPropertyRowMapper<>(Book.class);


    //----------AddBook------------------------------------------

    public Long AddBookInfo(User user,
        String title,String author,String isbn,String edition,Date publishtime,String publisher,String lang,String briefinfo
        ){
        Book book = new Book(title,author,isbn,edition,publishtime,publisher,lang,briefinfo);
        AddBook(book);
        return book.getId();
    }

    public void AddBook(Book book){
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO Book(title,author,copyamount,borrowedcopys,allborrowtimes,isbn,edition,publishtime,publisher,lang,briefinfo) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        if(1 != jdbctemplate.update((conn) -> {
                    PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setObject(1,book.getTitle());
                    ps.setObject(2,book.getAuthor());
                    ps.setObject(3,book.getCopyamount());
                    ps.setObject(4,book.getBorrowedcopys());
                    ps.setObject(5,book.getAllborrowtimes());
                    ps.setObject(6,book.getIsbn());
                    ps.setObject(7,book.getEdition());
                    ps.setObject(8,book.getPublishtime());
                    ps.setObject(9,book.getPublisher());
                    ps.setObject(10,book.getLang());
                    ps.setObject(11,book.getBriefinfo());
                    return ps;
                },
                holder)
        ){
            throw new RuntimeException("Insert failed.");
        }
        book.setId(holder.getKey().longValue());
    }


    public void DeleteBook(Long id){
        if(1 != jdbctemplate.update(
                "DELETE FROM Book WHERE id = ?",
                id
                )
            ){
                throw new RuntimeException("Failed to delete");
            }
    }

    //---------GetBookByAttribute------------------------------
    public Book GetBookbyBID(Long bid){
        String sql = "SELECT * FROM Book WHERE id = ?";
        return jdbctemplate.queryForObject(sql,
                (ResultSet rs, int rowNum) -> {
                    return new Book(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("copyamount"),
                        rs.getInt("borrowedcopys"),
                        rs.getInt("allborrowtimes"),
                        rs.getString("isbn"),
                        rs.getString("edition"),
                        rs.getDate("publishtime"),
                        rs.getString("publisher"),
                        rs.getString("lang"),
                        rs.getString("briefinfo")
                    );
                }
                ,bid
        );
    }

    public List<Book> GetBooksbyTitleOrAuthor(String s){
        return jdbctemplate.query(
            "SELECT * FROM Book WHERE title LIKE CONCAT(\'%\',?,\'%\') OR author LIKE CONCAT(\'%\',?,\'%\') ORDER BY allborrowtimes DESC",
            bookRowMapper,
            s,
            s
        );
    }

    public List<Book> GetBooksbyTitle(String Title){
        return jdbctemplate.query(
            "SELECT * FROM Book WHERE title LIKE CONCAT(\'%\',?,\'%\') ORDER BY allborrowtimes DESC",
            bookRowMapper,
            Title
        );
    }

    public List<Book> GetBooksbyAuthor(String Author){
        return jdbctemplate.query(
            "SELECT * FROM Book WHERE author LIKE CONCAT(\'%\',?,\'%\') ORDER BY allborrowtimes DESC",
            bookRowMapper,
            Author
        );
    }

    public List<Book> GetALlBooks(){
        return jdbctemplate.query("SELECT * FROM Book",bookRowMapper);
    }

    //----------ChangBookAttribute------------------------------
    public void ChangeBookAttribute(Book book,String attribute,Object value){
        try {
            Field field = book.getClass().getDeclaredField(attribute);
            field.setAccessible(true);
            field.set(book,value);
            if(1 != jdbctemplate.update(
                "UPDATE Book SET " + attribute + " = ? WHERE id = ? ",
                //attribute,
                field.get(book),
                book.getId()
                )
            ){
                throw new RuntimeException("Failed to change");
            }
        } catch (Exception e) {
            throw new RuntimeException(e); 
        }
    }
    
}
