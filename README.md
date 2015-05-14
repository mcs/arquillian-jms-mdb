# Arquillian JMS MDB Example

## Purpose
There are a few web resources showing how to test JMS sender and receiver, 
but I did not find any useful tests for Message-Driven Beans. This example shows one way 
how you can test Message-Driven Beans with Arquillian.

## Prerequisites
You need an installation of a *JBoss 7.2.0.Final*. I chose this one as it supports easy out-of-the-box support for JMS testing.
If you have another version installed, you need to change the version of *jboss-as-arquillian-container-managed*.
