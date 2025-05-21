smallfile 最大32G
```
create tablespace ...
```
bigfile 最大32T
```
create bigfile tablespace ...
```




> **BIGFILE | SMALLFILE**
>
> Use this clause to determine whether the tablespace is a bigfile or smallfile tablespace. This clause overrides any default tablespace type setting for the database.
>
> - A bigfile tablespace contains only one data file or temp file, which can contain up to approximately 4 billion (2^32) blocks. The minimum size of the single data file or temp file is 12 megabytes (MB) for a tablespace with 32K blocks and 7MB for a tablespace with 8K blocks. The maximum size of the single data file or temp file is 128 terabytes (TB) for a tablespace with 32K blocks and 32TB for a tablespace with 8K blocks.
> - A smallfile tablespace is a traditional Oracle tablespace, which can contain 1022data files or temp files, each of which can contain up to approximately 4 million (2^22) blocks.
>
> If you omit this clause, then Oracle Database uses the current default tablespace type of permanent or temporary tablespace that is set for the database. If you specify `BIGFILE` for a permanent tablespace, then the database by default creates a locally managed tablespace with automatic segment-space management.
