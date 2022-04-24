package com.res.app;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value="/book")
@AllArgsConstructor
public class BookController {
    BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooksRecords(){
        return  bookRepository.findAll();
    }

    @GetMapping(value = "{bookId}")
    public Book getBookById(@PathVariable(value ="bookId") Long id){
        return bookRepository.findById(id).get();
    }

    @PostMapping
    public Book createBookRecords(@RequestBody @Valid Book bookRecord){
        return bookRepository.save(bookRecord);
    }

    @PutMapping
    public Book updateBookRecord(@RequestBody @Valid Book bookRecord) throws Exception {
        if(bookRecord==null || bookRecord.getBooId()==null){
            throw new NotFoundException("BookRecord or Id must not be null!");
        }
        Optional<Book> optionalBook=bookRepository.findById(bookRecord.getBooId());
        if(optionalBook.isPresent()){
            throw new NotFoundException("Book with ID : "+bookRecord.getBooId()+" does not exist!");
        }
        Book existingBook=optionalBook.get();
        existingBook.setName(bookRecord.getName());
        existingBook.setSummary(bookRecord.getSummary());
        existingBook.setRating(bookRecord.getRating());

        return bookRepository.save(existingBook);
    }


}
