const ExtractTextPlugin = require("extract-text-webpack-plugin");
const path = require('path');
const webpack = require('webpack');

const extractSass = new ExtractTextPlugin({
  filename: "bundle.css"
});

const devServerPortNum = 9000;

module.exports = {
  entry: [
    './src/main/js/index.js',
    './src/main/resources/static/sass/main.scss'
  ],
  devtool: 'sourcemaps',
  cache: true,
  output: {
    path: path.resolve(__dirname, 'src', 'main', 'resources', 'static', 'build'),
    filename: 'bundle.js'
  },
  devtool: 'source-map',
  devServer: {
    port: devServerPortNum,
    proxy: {
      '/': {
        target: 'http://localhost:8080',
        secure: false,
      }
    },
    publicPath: 'http://localhost:' + devServerPortNum + '/',
    historyApiFallback: true
  },
  module: {
    rules: [
      {
        test: /\.(sass|scss)$/,
        exclude: /node-modules/,
        use: extractSass.extract({
          use: [{
            loader: 'css-loader'
          }, {
            loader: 'sass-loader'
          }],
          fallback: 'style-loader'
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
