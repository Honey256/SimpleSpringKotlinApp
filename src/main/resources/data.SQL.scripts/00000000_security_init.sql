CREATE TABLE table_user(
  id NUMERIC(38) NOT NULL PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);

COMMENT ON TABLE table_user is 'Таблица для хранения пользователей';
COMMENT ON COLUMN table_user.username is 'Системное имя пользователя';
COMMENT ON COLUMN table_user.password is 'Пароль пользователя';


CREATE TABLE role(
  id NUMERIC(38) NOT NULL PRIMARY KEY,
  systemname VARCHAR(100) NOT NULL,
  description VARCHAR(255)
);

COMMENT ON TABLE role is 'Таблица для хранения ролей пользователя';
COMMENT ON COLUMN role.systemname is 'Системное имя роли';
COMMENT ON COLUMN role.description is 'Описание роли';


CREATE TABLE user_roles(
  user_id NUMERIC(38) NOT NULL,
  role_id NUMERIC(38) NOT NULL,

  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES table_user(id),
  CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES role(id),
  CONSTRAINT uq_user_id_role_id UNIQUE (user_id, role_id)
);

COMMENT ON TABLE user_roles is 'Кросс-таблица пользователь - роль';
COMMENT ON COLUMN user_roles.user_id is 'Идентификатор пользователя';
COMMENT ON COLUMN user_roles.role_id is 'Идентификатор роли';

INSERT INTO table_user (id, username, password) VALUES (1, 'honey256', '123q123');

INSERT INTO role VALUES (1, 'ROLE_LISTENER', 'Слушатель докладов');
INSERT INTO role VALUES (2, 'ROLE_ADMIN', 'Админ');
INSERT INTO role VALUES (3, 'ROLE_PRESENTER', 'Докладчик');
INSERT INTO user_roles VALUES (1, 2);

