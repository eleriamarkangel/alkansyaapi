select * from alkansya.bank_accounts;
select * from alkansya.bank_customers;

select bc.customer_number, ba.account_id,
ba.debit_balance, ba.credit_balance
from alkansya.bank_accounts ba, alkansya.bank_customers bc
where ba.customer_number = bc.customer_number

