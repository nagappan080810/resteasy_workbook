<html>
    <body>
        <h1>JAX-RS File Upload Example</h1>
        <form action="rest/upload-file" method="post" enctype="multipart/form-data">
            <p>
                File name : <input type="text" name="fileName" />
            </p>
            <p>
                Choose the file : <input type="file" name="selectedFile" />
            </p>
            <input type="submit" value="Upload" />
        </form>
    </body>
</html>