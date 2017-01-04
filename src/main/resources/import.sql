INSERT INTO t_user (USER_ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, IS_ACTIVE, created_date,is_matrimony_user) VALUES (1, 'admin@admin.com', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, now(),1);

INSERT INTO t_role (ROLE_ID, role_name) VALUES (1, 'ROLE_USER');
INSERT INTO t_role (ROLE_ID, role_name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_role (USER_ID, ROLE_ID) VALUES (1, 1);
INSERT INTO user_role (USER_ID, ROLE_ID) VALUES (1, 2);


