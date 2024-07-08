package com.vudd.reactiveprogrammingplayground.sec02;

import com.vudd.reactiveprogrammingplayground.common.Util;
import com.vudd.reactiveprogrammingplayground.sec02.assignment.FileService;
import com.vudd.reactiveprogrammingplayground.sec02.assignment.FileServiceImpl;

public class Lec12Assignment {
    public static void main(String[] args) {
        // Assignment: Implement the FileService interface
        // 1. Read a file
        // 2. Write a file
        // 3. Delete a file
        // 4. Use the FileServiceImpl class to implement the FileService interface
        // 5. Use the FileServiceImpl class to test the implementation

         FileService fileService = new FileServiceImpl();

         fileService.write("file.txt", "This is a test file")
                         .subscribe(
                                 Util.subscriber()
                         );

        fileService.read("file.txt")
                .subscribe(
                        Util.subscriber()
                );

        fileService.delete("file.txt").subscribe(Util.subscriber());
    }

}
