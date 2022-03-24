FROM node:lts-alpine3.14 as build
WORKDIR /home/app
COPY ./ /home/app/
RUN npm install
RUN npm run build --configuration=production --output-path=/home/app/dist/


FROM nginx:latest
COPY --from=build /home/app/dist/dt-angular /usr/share/nginx/html
EXPOSE 80
