insert into ADDRESSBOOK values(101, 'amazon');
insert into ADDRESSBOOK values(102, 'flipkart');

insert into CONTACT (id, name, phone, address_book_id) values(1000, 'Customer1', '9812345678', 101);
insert into CONTACT (id, name, phone, address_book_id) values(1001, 'Customer2', '9812345679', 101);
insert into CONTACT (id, name, phone, address_book_id) values(1002, 'Customer3', '9812345678', 102);
insert into CONTACT (id, name, phone, address_book_id) values(1003, 'Customer4', '9812345679', 102);