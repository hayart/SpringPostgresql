CREATE TABLE IF NOT EXISTS AFFILIATE_CLIENT_MAP (
    id INT NOT NULL,
    referralCode varchar(250) NULL,
    clickId varchar(250) NULL,
    userAgent varchar(250) NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS AFFILIATE_TRANSACTIONS (
    id INT NOT NULL,
    client_id varchar(250) NULL,
    referralCode varchar(250) NULL,
    clickId varchar(250) NULL,
    orderId varchar(250) NULL,
    currency varchar(250) NULL,
    conversionAmount varchar(250) NULL,
    orderAmount varchar(250) NULL,
    transactionType varchar(250) NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS AFFILIATE_CLIENT_MAP (
    id INT NOT NULL,
    client_id varchar(250) NULL,
    request_type varchar(250) NULL,
    payload varchar(250) NULL,
    reason_of_failure varchar(250) NULL,
    time_of_failure varchar(250) NULL,
    procesed varchar(250) NULL,
    PRIMARY KEY (id)
);