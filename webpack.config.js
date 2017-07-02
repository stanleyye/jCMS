var path = require('path');
var webpack = require('webpack');

module.exports = {
  entry: './src/main/js/index.js',
  devtool: 'sourcemaps',
  cache: true,
  output: {
    path: path.resolve(__dirname, 'src', 'main', 'resources', 'static', 'built'),
    filename: 'bundle.js'
  },
  devtool: 'sourcemap',
  module: {
    rules: [
      {
        test: /\.css$/,
        use: [ 'style-loader', 'css-loader' ]
      },
      {
        test: /\.js$/,
        loader: 'babel-loader',
        exclude: /node-modules/,
        query: {
          presets: ['es2015', 'react']
        }
      }
    ]
  }
};