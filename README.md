# Test-project

Program for calculating best subnet for given IP address.\
The subnet is selected from the generated subnets, whose number is also given.
Result subnet will be written to file `out.txt.`
Generated subnets will be written to file `autogen.txt`.
Number of subnets and IP should be written in input file `in.txt` in following format:
``` 
    number-of-networks
    IP-address
```

To execute program enter following command:\
``docker run -v `pwd`/<path-to-input>/:/mnt/data/ katevi/getting-best-subnet:latest``\
when `pwd` is your current directory and `<path-to-input>` is path to file `in.txt`.