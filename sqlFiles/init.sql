DELETE FROM items_in_cart;

DELETE FROM purchase_details;
DELETE FROM purchases;
DELETE FROM items;
DELETE FROM categories;
DELETE FROM users;
DELETE FROM administrators;


/*既にテーブルが構築されている想定*/

DROP SEQUENCE IF EXISTS SEQ_CAT_CATID CASCADE;
DROP SEQUENCE IF EXISTS SEQ_ITEMS_ITEMID CASCADE;
DROP SEQUENCE IF EXISTS SEQ_PURCHASE_ID CASCADE;
DROP SEQUENCE IF EXISTS SEQ_PUR_DETAIL_ID CASCADE;


CREATE SEQUENCE SEQ_CAT_CATID;
CREATE SEQUENCE SEQ_ITEMS_ITEMID;
CREATE SEQUENCE SEQ_PURCHASE_ID;
CREATE SEQUENCE SEQ_PUR_DETAIL_ID;


ALTER SEQUENCE public.SEQ_CAT_CATID OWNER TO ecsite;
ALTER SEQUENCE public.SEQ_ITEMS_ITEMID OWNER TO ecsite;
ALTER SEQUENCE public.SEQ_PURCHASE_ID OWNER TO ecsite;
ALTER SEQUENCE public.SEQ_PUR_DETAIL_ID OWNER TO ecsite;


ALTER TABLE categories ALTER COLUMN "category_id" SET DEFAULT nextval('SEQ_CAT_CATID');
ALTER TABLE items  ALTER COLUMN "item_id" SET DEFAULT nextval('SEQ_ITEMS_ITEMID');
ALTER TABLE purchases ALTER COLUMN "purchase_id" SET DEFAULT nextval('SEQ_PURCHASE_ID');
ALTER TABLE purchase_details ALTER COLUMN "purchase_detail_id" SET DEFAULT nextval('SEQ_PUR_DETAIL_ID');

INSERT INTO administrators (admin_id, password, name) VALUES ('admin', 'admin', '管理者');
INSERT INTO public.users(user_id,password,name,address) VALUES ('user','userpass','橋本広大','鳥取県鳥取市河原町');

INSERT INTO categories (category_id,name) VALUES (0,'すべて');
INSERT INTO categories (category_id,name) VALUES (1,'帽子');
INSERT INTO categories (category_id,name) VALUES (2,'鞄');

INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('麦わら帽子','日本帽子製造',1,'黄色',4980,12,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ストローハット','(株)ストローハットジャパン',1,'茶色',3480,15,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('子ども用麦わら帽子','鳥取帽子店',1,'赤色',2980,3,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('つくつくぼうし','(株)ツクツクボウシジャパン',1,'青色',4480,6,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('野球帽','日本帽子製造',1,'緑色',2500,17,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ニットキャップ','日本帽子製造',1,'紺色',1800,9,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ハンチング帽','日本帽子製造',1,'黄色',1980,20,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ヒヤリハット','(株)ヒヤリジャパン',1,'茶色',5480,2,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ターバン','鳥取帽子店',1,'赤色',4580,1,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ベレー帽','鳥取帽子店',1,'青色',3200,8,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('マジック用ハット','鳥取帽子店',1,'緑色',650,17,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('自転車鞄','鳥取鞄店',2,'青色',1980,18,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('防災鞄','鳥取鞄店',2,'緑色',4980,15,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('エースの鞄','エース',2,'紺色',2200,3,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('吉田鞄','吉田鞄',2,'黄色',2980,6,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('バーマスの鞄','バーマス',2,'茶色',780,17,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('エンドー鞄','エンドー',2,'赤色',2500,9,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ブリーフィングの鞄','ブリーフィング',2,'青色',1800,20,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('マスターピースの鞄','マスターピース',2,'緑色',1980,2,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ラガシャの鞄','ラガシャ',2,'茶色',690,1,FALSE);


/*insert into items_in_cart values('user',1,5,'2020/10/20');
insert into items_in_cart values('user',2,1,'2020/10/20');
insert into items_in_cart values('user',3,3,'2020/10/20');*/

/*テスト用　購入完了処理の在庫不足用*/
/*insert into items_in_cart values('user',4,7,'2020/10/20')*/

/*purchases、purchase_detailsのテスト用レコード*/

/*insert into purchases (purchased_user,purchased_date,destination,cancel) values('user','2025-06-17','テスト',false);

insert into purchase_details(purchase_id,item_id,amount) values(1,19,1);*/



/*insert into purchases values(1,'user',current_date,'テスト',false);*/
/*insert into purchase_details values(1,1,20,1);*/





