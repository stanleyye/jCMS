const ExtractTextPlugin = require("extract-text-webpack-plugin");
const path = require('path');
const webpack = require('webpack');

const extractSass = new ExtractTextPlugin({
  filename: "bundle.css"
});


module.exports = {
  // TODO: dynamically create an entry point for each js file in the /pages directory.
  // See https://stackoverflow.com/questions/40723947/is-it-possible-to-dynamically-create-an-entry-point-for-each-folder-in-the-webpa
  entry: [
    './src/main/js/index.js', 
    // './src/main/js/pages/login-page.js',
    // './src/main/js/pages/register-page.js',
    './src/main/resources/static/sass/main.scss'
  ],
  devtool: 'sourcemaps',
  cache: true,
  output: {
    path: path.resolve(__dirname, 'src', 'main', 'resources', 'static', 'build'),
    filename: 'bundle.js'
  },
  devtool: 'source-map',
  module: {
    rules: [
      {
        test: /\.(sass|scss)$/,
        exclude: /node-modules/,
        use: extractSass.extract({
          use: [{
            loader: "css-loader"
          }, {
            loader: "sass-loader"
          }],
          fallback: "style-loader"
        }),
      },
      {
        test: /\.(js|jsx)$/,
        loader: 'babel-loader',
        exclude: /node-modules/,
        query: {
          presets: ['es2015', 'react']
        }
      }
    ]
  },
  plugins: [
    extractSass
  ]
};