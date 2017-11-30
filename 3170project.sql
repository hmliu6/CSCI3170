/*5.1. Administrator*/

/*Create tables in the database:*/


/*Delete tables in the database:*/
DROP TABLE `author`, `book`, `category`, `checkout_record`, `copy`, `user`;

/*Load data from a dataset:*/

/*Show the number of records in each table:*/
SELECT COUNT(*) FROM $table_name

/*5.2. Library User*/

/*Search for books
o By call number (exact matching)*/
SELECT * FROM book WHERE book.call_number = $call_number;

/*o By title (partial matching)*/
SELECT * FROM book WHERE book.title like "%".$title."%";

/*o By author (partial matching)*/
SELECT book.call_number FROM book WHERE book.call_number IN (SELECT author.call_number FROM author WHERE author.name LIKE "%".$auther."%";
/*or*/
SELECT book.call_number, book.title, author.name AS `Author`, COUNT(copy.copy_number)-COUNT(checked_out.lent) AS `Available No of Copies` FROM book, author, (SELECT checkout_record.call_number, COUNT(checkout_record.copy_number) AS lent FROM checkout_record WHERE checkout_record.return_date IS NOT NULL GROUP BY checkout_record.call_number) checked_out WHERE book.call_number=author.call_number AND book.call_number IN (SELECT author.call_number FROM author WHERE author.name LIKE "%"

/*Borrow a book copy*/
/*1. Check if the target copy is borrowed by others*/
SELECT checkout_record.call_number, checkout_record.copy_number FROM checkout_record WHERE checkout_record.call_number = $call_number AND checkout_record.copy_number = $copy_number AND checkout_record.return_date IS NULL;

/*2. If the target copy is not borrowed, insert checkout record*/
INSERT INTO `checkout_record` (`user_id`, `call_number`, `copy_number`, `checkout_date`, `return_date`) VALUES ($user_id, $call_number, $copy_number, $checkout_date, '');

/*Return a book copy*/
/*1. Check if the target copy is borrowed by others*/
SELECT * FROM checkout_record WHERE checkout_record.user_id = '' AND checkout_record.call_number = '' AND checkout_record.copy_number = '' AND checkout_record.return_date IS NULL;

/*2. If the record exists, insert the return_date*/
INSERT INTO `checkout_record` (`return_date`) VALUES ($return_date) WHERE checkout_record.user_id = '' AND checkout_record.call_number = '' AND checkout_record.copy_number = '' AND checkout_record.return_date IS NULL;

/*List all unreturned book copies which are checked-out within a period*/
SELECT checkout_record.user_id, checkout_record.call_number, checkout_record.copy_number, checkout_record.checkout_date FROM checkout_record checkout_record WHERE checkout_record.checkout_date >= $start_date AND checkout_record.checkout_date <= $end_date AND return_date IS NULL;












