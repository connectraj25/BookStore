INSERT INTO book (name, description, author, type, price, isbn) VALUES ('Burning Down the House: Essays on Fiction', 'Sample Description', 'Baxter, Charles', 'fiction', 15.00, 1555975089);
INSERT INTO book (name, description, author, type, price, isbn) VALUES ('No Knives in the Kitchens of This City: A Novel', 'Sample Description', 'Khalifa, Khaled','fiction', 16.95 , 9774167813);
INSERT INTO book (name, description, author, type, price, isbn) VALUES ('Comic-Con Episode IV: A Fans Hope', 'Sample Description', 'Spurlock, Morgan', 'comic', 10.95, 0756683424);
INSERT INTO book (name, description, author, type, price, isbn) VALUES ('Perspective! for Comic Book Artists: How to Achieve a Professional Look in your Artwork', 'Sample Description', 'Chelsea, David', 'comic', 35.00, 0823005674);
INSERT INTO promotion (promocode, booktype, discountprice) VALUES ('PROMOFICTION', 'fiction', 10.00);
INSERT INTO promotion (promocode, booktype, discountprice) VALUES ('PROMOCOMIC', 'comic', 0.00);
INSERT INTO promotion (promocode, booktype, discountprice) VALUES ('PROMONOVEL', 'novel', 20.00);
INSERT INTO promotion (promocode, booktype, discountprice) VALUES ('PROMOALL', null, 5.00);
