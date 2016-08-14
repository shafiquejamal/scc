Shafique Jamal's Coding Challenge for Sortable

## Requirements

- scala 2.11
- java 1.8
- SBT

## Instructions for running this

In a *nix terminal:

```
cd /path/to/some/directory
git clone https://github.com/shafiquejamal/scc sjchallenge
cd sjchallenge
sbt run "/full/path/to/products.txt" "/full/path/to/listings.txt" "/full/path/to/non-existent-output-file.txt"
```

The output file will be: /full/path/to/non-existent-output-file.txt

## Notes

- Entries in the given listings.txt files are not unique - I do not remove duplicates
