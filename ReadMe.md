master: [![Build Status](https://travis-ci.org/mmarciniuk/document-archive.svg?branch=master)](https://travis-ci.org/mmarciniuk/document-archive)

develop: [![Build Status](https://travis-ci.org/mmarciniuk/document-archive.svg?branch=develop)](https://travis-ci.org/mmarciniuk/document-archive)

# Scope
* Creating account
* Login/Logout
* Adding document's for logged user:
  * Files:
    * doc
    * pdf
  * Adding meta data for document
* Listing added documents
  * User can see only documents added by himself
* Editing meta data information
* Removing added document
* Web GUI
* REST API

Preparing data base:

docker run --name document-archiver-db -p 3337:3306 -e MYSQL_ROOT_PASSWORD=secretPassword -d mysql:8.0.17