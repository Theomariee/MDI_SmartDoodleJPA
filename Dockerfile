FROM node:lts-alpine

WORKDIR /app

COPY ./frontend/vue-app/package*.json /app

RUN sudo apt-get update 
RUN sudo apt-get install nodejs npm

RUN npm install

COPY . /app

EXPOSE 3000

RUN npm run serve -- --3000
