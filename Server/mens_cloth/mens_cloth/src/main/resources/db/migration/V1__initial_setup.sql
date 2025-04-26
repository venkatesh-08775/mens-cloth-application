CREATE TABLE "user"(
id BIGSERIAL PRIMARY KEY,
name VARCHAR(200) NOT NULL,
email VARCHAR(300) UNIQUE NOT NULL,
mobile VARCHAR(200),
password TEXT,
sign_in_type VARCHAR(50) CHECK(sign_in_type IN(
'EMAIL_PASSWORD',
'GOOGLE'
)),
is_verified BOOLEAN DEFAULT false,
status VARCHAR(50) CHECK(status IN(
'ACTIVE',
'INACTIVE'
)) DEFAULT 'ACTIVE',
created_at TIMESTAMP DEFAULT now(),
updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE dashboard_user(
id BIGSERIAL PRIMARY KEY,
user_id BIGINT REFERENCES "user"(id) ON DELETE CASCADE,
email VARCHAR(200) NOT NULL,
role VARCHAR(50) CHECK(role IN ('OWNER','MANAGER' ,'STAFF')) NOT NULL,
invitation_by BIGINT REFERENCES "user"(id),
invitation_status VARCHAR(50) CHECK (invitation_status IN('PENDING','ACCEPT','DECLINED')) DEFAULT 'PENDING',
invitation_token TEXT UNIQUE,
invitation_token_expire TIMESTAMP,
created_at TIMESTAMP DEFAULT now(),
updated_at TIMESTAMP DEFAULT now()

);

CREATE TABLE category(
id BIGSERIAL PRIMARY KEY,
name VARCHAR(200) UNIQUE NOT NULL,
image_url TEXT,
description TEXT,
added_by BIGINT REFERENCES "user"(id),
status VARCHAR(50) CHECK (status IN ('ACTIVE','INACTIVE','ARCHIVED')) DEFAULT 'ACTIVE' ,
created_at TIMESTAMP DEFAULT now(),
updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE variant(
id BIGSERIAL PRIMARY KEY,
name VARCHAR(200) UNIQUE NOT NULL,
image_url TEXT,
variant_value TEXT,
created_at TIMESTAMP DEFAULT now(),
updated_at TIMESTAMP DEFAULT now()
);
CREATE TABLE file(
id BIGSERIAL PRIMARY KEY,
image_url TEXT,
type VARCHAR(50) CHECK (type IN ('CATEGORY','PRODUCT','PROFILE'))  DEFAULT '',
created_at TIMESTAMP DEFAULT now(),
updated_at TIMESTAMP DEFAULT now()
);



CREATE TABLE product(
id BIGSERIAL PRIMARY KEY,
category_id BIGINT REFERENCES category(id) ON DELETE CASCADE,
name VARCHAR(200) UNIQUE NOT NULL,
image_url TEXT,
description TEXT,
added_by BIGINT REFERENCES "user"(id),
base_price  DECIMAL(10,2) NOT NULL,
discount DECIMAL(10,2) NOT NULL,
final_price DECIMAL(10,2) NOT NULL,
variant_id BIGINT REFERENCES variant(id),
created_at TIMESTAMP DEFAULT now(),
updated_at TIMESTAMP DEFAULT now()
);


CREATE TABLE customer(
id BIGSERIAL PRIMARY KEY,
name VARCHAR(200),
email VARCHAR(300) UNIQUE,
mobile TEXT,
address TEXT ,
country TEXT,
city TEXT,
state TEXT,
pin_code VARCHAR(50),
is_verified BOOLEAN DEFAULT false,
status VARCHAR(50) CHECK(status IN(
'ACTIVE',
'INACTIVE'
)) DEFAULT 'ACTIVE',
created_at TIMESTAMP DEFAULT now(),
updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE "order" (
  id BIGSERIAL PRIMARY KEY,
  customer_id BIGINT REFERENCES customer(id),
  status VARCHAR(50) CHECK (status IN('PROCESS','SHIPPING','COMPLETED','CANCEL','REFUND')) DEFAULT 'PROCESS',
  total_amount DECIMAL(10,2) NOT NULL,
  payment_method VARCHAR(100) CHECK (payment_method IN ('GOOGLE_PAY','PAYPAL')),
  payment_received BOOLEAN DEFAULT FALSE,
  created_at TIMESTAMP DEFAULT now(),
  updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE order_item (
  id BIGSERIAL PRIMARY KEY,
  order_id BIGINT REFERENCES "order"(id) ON DELETE CASCADE,
  product_id BIGINT REFERENCES product(id),
  quantity INT NOT NULL,
  total_price DECIMAL(10,2) NOT NULL,
  created_at TIMESTAMP DEFAULT now(),
  updated_at TIMESTAMP DEFAULT now()
);


CREATE TABLE cart(
id BIGSERIAL PRIMARY KEY,
product_id BIGINT REFERENCES product(id),
quantity INT ,
customer_id BIGINT REFERENCES customer(id),
created_at TIMESTAMP DEFAULT now(),
updated_at TIMESTAMP DEFAULT now()

);


CREATE TABLE wish_list(
id BIGSERIAL PRIMARY KEY,
product_id BIGINT REFERENCES product(id),
customer_id BIGINT REFERENCES customer(id),
created_at TIMESTAMP DEFAULT now(),
updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE feedback(

id BIGSERIAL PRIMARY KEY,
rating DECIMAL(10,2),
"like" INT,
comment TEXT,
product_id BIGINT REFERENCES product(id),
customer_id BIGINT REFERENCES customer(id),
created_at TIMESTAMP DEFAULT now(),
updated_at TIMESTAMP DEFAULT now()
);