drop table if exists book cascade;
drop table if exists author;

create table book (
    id bigint not null auto_increment primary key,
    isbn varchar(255),
    publisher varchar(255),
    title varchar(255),
    author_id bigint
) engine=InnoDB;

create table author (
    id bigint not null auto_increment primary key,
    first_name varchar(255),
    last_name varchar(255)
) engine=InnoDB;

alter table book add constraint book_author_fk foreign key (author_id) references author (id);

insert into author (first_name, last_name) values ('John', 'Doe');

insert into book (isbn, title, publisher, author_id)
    values ('1234567890', 'Spring In Action', 'Manning', (select id from author where first_name = 'John' and last_name = 'Doe'));

insert into book (isbn, title, publisher, author_id)
    values ('1234567891', 'Spring-Boot In Action, 1st Edition', 'Manning', (select id from author where first_name = 'John' and last_name = 'Doe'));

insert into book (isbn, title, publisher, author_id)
    values ('1234567892', 'Spring-Boot In Action, 6th Edition', 'Manning', (select id from author where first_name = 'John' and last_name = 'Doe'));


insert into author (first_name, last_name) values ('Miguel', 'de Cervantes');

insert into book (isbn, title, publisher, author_id)
    values ('1234567893', 'Don Quixote', 'Simon', (select id from author where first_name = 'Miguel' and last_name = 'de Cervantes'));

insert into book (isbn, title, publisher, author_id)
    values ('1234567894', 'El Quijote', 'Simon', (select id from author where first_name = 'Miguel' and last_name = 'de Cervantes'));


-- Adding new authors
INSERT INTO author (first_name, last_name) VALUES
('Harper', 'Lee'),
('George', 'Orwell'),
('F. Scott', 'Fitzgerald'),
('J.D.', 'Salinger'),
('Paulo', 'Coelho'),
('J.R.R.', 'Tolkien'),
('William', 'Shakespeare'),
('Jane', 'Austen'),
('Herman', 'Melville'),
('Dan', 'Brown'),
('Suzanne', 'Collins'),
('J.K.', 'Rowling'),
('Emily', 'Brontë'),
('Charles', 'Dickens'),
('Oscar', 'Wilde'),
('Bram', 'Stoker'),
('Mary', 'Shelley');

-- Adding books by these authors
INSERT INTO book (isbn, title, publisher, author_id)
VALUES
('9780061120084', 'To Kill a Mockingbird', 'J. B. Lippincott & Co.', (SELECT id FROM author WHERE first_name = 'Harper' AND last_name = 'Lee')),
('9780451524935', '1984', 'Secker & Warburg', (SELECT id FROM author WHERE first_name = 'George' AND last_name = 'Orwell')),
('9780743273565', 'The Great Gatsby', 'Charles Scribner''s Sons', (SELECT id FROM author WHERE first_name = 'F. Scott' AND last_name = 'Fitzgerald')),
('9780316769174', 'The Catcher in the Rye', 'Little, Brown and Company', (SELECT id FROM author WHERE first_name = 'J.D.' AND last_name = 'Salinger')),
('9780062315007', 'The Alchemist', 'HarperOne', (SELECT id FROM author WHERE first_name = 'Paulo' AND last_name = 'Coelho')),
('9780544003415', 'The Hobbit', 'George Allen & Unwin', (SELECT id FROM author WHERE first_name = 'J.R.R.' AND last_name = 'Tolkien')),
('9780743477109', 'Romeo and Juliet', 'Thomas Creede', (SELECT id FROM author WHERE first_name = 'William' AND last_name = 'Shakespeare')),
('9780141439518', 'Pride and Prejudice', 'T. Egerton, Whitehall', (SELECT id FROM author WHERE first_name = 'Jane' AND last_name = 'Austen')),
('9780142437207', 'Moby-Dick', 'Harper & Brothers', (SELECT id FROM author WHERE first_name = 'Herman' AND last_name = 'Melville')),
('9780307474278', 'The Da Vinci Code', 'Doubleday', (SELECT id FROM author WHERE first_name = 'Dan' AND last_name = 'Brown')),
('9780439023528', 'The Hunger Games', 'Scholastic', (SELECT id FROM author WHERE first_name = 'Suzanne' AND last_name = 'Collins')),
('9780439554893', 'Harry Potter and the Philosopher''s Stone', 'Bloomsbury', (SELECT id FROM author WHERE first_name = 'J.K.' AND last_name = 'Rowling')),
('9780743477108', 'Macbeth', 'Edward Blount and William Jaggard', (SELECT id FROM author WHERE first_name = 'William' AND last_name = 'Shakespeare')),
('9780743477566', 'Hamlet', 'Simon & Schuster', (SELECT id FROM author WHERE first_name = 'William' AND last_name = 'Shakespeare')),
('9780141439600', 'Wuthering Heights', 'Thomas Cautley Newby', (SELECT id FROM author WHERE first_name = 'Emily' AND last_name = 'Brontë')),
('9780141439519', 'Emma', 'John Murray', (SELECT id FROM author WHERE first_name = 'Jane' AND last_name = 'Austen')),
('9780141439564', 'Great Expectations', 'Chapman & Hall', (SELECT id FROM author WHERE first_name = 'Charles' AND last_name = 'Dickens')),
('9780141439595', 'The Picture of Dorian Gray', 'Lippincott''s Monthly Magazine', (SELECT id FROM author WHERE first_name = 'Oscar' AND last_name = 'Wilde')),
('9780141439748', 'Dracula', 'Archibald Constable and Company', (SELECT id FROM author WHERE first_name = 'Bram' AND last_name = 'Stoker')),
('9780141439793', 'Frankenstein', 'Lackington, Hughes, Harding, Mavor & Jones', (SELECT id FROM author WHERE first_name = 'Mary' AND last_name = 'Shelley'));