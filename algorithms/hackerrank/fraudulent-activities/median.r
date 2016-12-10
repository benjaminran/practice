#!/usr/bin/env Rscript

d<-scan("stdin", quiet=TRUE)
cat(median(d), sep="\n")
