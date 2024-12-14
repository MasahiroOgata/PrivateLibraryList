/* テスト用ユーザーデータ1
   id = 1
   ユーザー名 = "test01"
   パスワード = "testpass01" */
insert into users 
(user, pass, nickname)
values
("test01",
"$2a$10$gZUPYDZO9Bj4FwWfknE8oeExb8aSWAsLnygAAzI7bGSBp7xPDxG9S",
"testUser01");

/* テスト用ユーザーデータ2
   id = 2
   ユーザー名 = "test02"
   パスワード = "testpass02" */
insert into users 
(user, pass, nickname)
values
("test02",
"$2a$10$rJzOrMQvsfNY2cU6NNFpyOaW.zCqkJPyWpv25qNPGZFVQb6EqipUK",
"testUser02");
