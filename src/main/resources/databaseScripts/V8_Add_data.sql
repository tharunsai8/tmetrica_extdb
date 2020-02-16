insert into users(email, password, name)
VALUES ('admin@test.com', '$2a$12$Q.9c5XxwNTWXDaQvGhxs4OR5PlJiF2vzm5My4ywAgHxJDawHCZv6W', 'Admin Name');
insert into users(email, password, name)
VALUES ('user@tes.com', '$2a$12$pegFHPkjlEVbvc9hKx8xdupYjbyQ09xlkzlRh0UIjbOKus1ehshzG', 'User Name');
insert into user_roles(user_id, role)
VALUES (1, 'ADMIN');
insert into user_roles(user_id, role)
VALUES (1, 'USER');
insert into user_roles(user_id, role)
VALUES (2, 'USER');