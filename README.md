# Online Cafetaria web application for UPT
Designed, developed and tested by:
- 48543 Rainier Bastiaans
- 48542 Raf Dadoumont
- 48519 Jan Wieprow
- 40578 Rúben Santos

## Frontend 
#### Framework: React with Typescript
### *Getting started with the front-end*
#### Create a Next.js app - DONE already

First, you need to create a Next.js app in this directory. In a terminal, execute:

```console
> npx create-next-app@latest --typescript
```

#### 1) Run `npm i` to install dependencies/modules
(These are ignored by default as visible in the .gitignore file)

*! This info is also available in the frontend/README.md file*

#### 2) Run server
Start the development server by opening a terminal in the `frontend` folder and running:

We will be running the back-end on port `8080`. = default for Spring.
We will be running the front-end on port `3000`. = default for React.

```bash
npm run start
# or
yarn start
# or
pnpm start
```

Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

You can start by modifying `pages/index.tsx` and develop React components. 
The page auto-updates as you edit the file.

*! This info is also available in the frontend/README.md file*

#### Learn More

To learn more about Next.js and React, take a look at the following resources:

-   [Next.js Documentation](https://nextjs.org/docs)
-   [React Documentation](https://reactjs.org/docs/getting-started.html)

## Backend 
#### Framework: Spring Boot
### Documentation
All API endpoints are testable with the Swagger UI accessable on:
http://localhost:8080/swagger-ui/index.html#/
