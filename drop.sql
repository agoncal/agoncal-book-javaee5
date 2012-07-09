ALTER TABLE t_order_order_line DROP CONSTRAINT trdrorderlinerdrfk
ALTER TABLE t_order_order_line DROP CONSTRAINT trdrrdrlinerdrlnfk
ALTER TABLE t_item DROP CONSTRAINT t_item_product_fk
ALTER TABLE t_order DROP CONSTRAINT t_ordercustomer_fk
ALTER TABLE t_order DROP CONSTRAINT t_order_address_fk
ALTER TABLE t_order_line DROP CONSTRAINT torder_lineitem_fk
ALTER TABLE t_product DROP CONSTRAINT tproductcategoryfk
ALTER TABLE t_customer DROP CONSTRAINT tcustomeraddressfk
DROP TABLE t_order_order_line
DROP TABLE t_item
DROP TABLE t_category
DROP TABLE t_order
DROP TABLE t_order_line
DROP TABLE t_address
DROP TABLE t_product
DROP TABLE t_customer
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN'
