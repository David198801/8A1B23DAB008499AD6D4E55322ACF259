

```javascript
buf.append(" select sum(case when fdc = 'J' then fmoney else -fmoney end) as money from t_fa_voucher where ");
buf.append(" fproduct_id = ? and fbus_type = ? and fitem_code = '3003_ZQQSK_TBXJCE' and fchecked = 1 and fdate = ? ");
```

