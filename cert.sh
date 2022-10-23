#!/bin/zsh

keytool -genkeypair -keystore myKeystore2.p12 -storetype PKCS12 -storepass 123456 -alias ks-localhost -keyalg RSA -keysize 2048 -validity 99999 -dname "CN=My SSL Certificate, OU=My Team, O=My Company, L=My City, ST=My State, C=SA" -ext "SAN:c=DNS:localhost,IP:127.0.0.1"
mv myKeystore2.p12 ./src/main/resources/keystore/
# https://stackoverflow.com/a/31900210