require('dotenv').config();

const express = require("express");
const axios = require("axios");

const CLIENT_ID = process.env.CLIENT_ID;
const CLIENT_SECRET = process.env.CLIENT_SECRET;
const MERCHANT_SHOP = "fr.myshopify.com";
const PERMISSION_SCOPES = "read_orders,write_products,read_customers";
const INSTALL_URL = "https://" + MERCHANT_SHOP + "/admin/oauth/authorize?client_id=" + CLIENT_ID + "&scope=" + PERMISSION_SCOPES + "&redirect_uri=";

const app = express();
const path = require('path');
app.engine('html', require('ejs').renderFile);
app.set('view engine', 'html');

app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, '/index.html'));
});

app.get("/install", (req, res) => {
  res.redirect(301, INSTALL_URL)
});

const PORT = 80;
app.listen(PORT, () => {
  console.log(`Server started at port ${PORT}`);
});
