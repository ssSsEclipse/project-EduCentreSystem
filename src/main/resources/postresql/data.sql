DELETE FROM USERS;
INSERT INTO USERS (username, password, active, role) VALUES ('admin', '$2a$06$CLzncWyTDxk90/jPUum/tu49s1qHMHyIJfmHXiV38nuXzhlIAE4.G', true, 'ROLE_ADMIN');
INSERT INTO USERS (username, password, active, role) VALUES ('user1', '$2a$06$npGoWwSUhdy/xDVWbMZO3eQfGOTJInIcwTEFtg39qQehwh2xahg46', true, 'ROLE_USER');
INSERT INTO USERS (username, password, active, role) VALUES ('user2', '$2a$06$qTUgxlayCXKVIiMbAM3rD.eLiftmOireGfQN6nlDBgF.6FRezlVt2', false, 'ROLE_USER');