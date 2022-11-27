FROM node:16.15

WORKDIR /web-app

COPY web-app/package-lock.json /web-app/package-lock.json
COPY web-app/package.json /web-app/package.json

RUN npm ci --legacy-peer-deps

COPY web-app/ /web-app/