<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="assets/global/plugins/bootstrap-fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
<link href="assets/global/plugins/bootstrap-fileinput/themes/explorer/theme.css" rel="stylesheet">


<script src="assets/global/plugins/bootstrap-fileinput/js/fileinput.js" type="text/javascript"></script>
<script src="assets/global/plugins/bootstrap-fileinput/themes/explorer/theme.js"></script>


</head>
<body>
	<div class="file-loading">
    <input class="input-ke-2" name="input-ke-2[]" type="file" multiple>
</div>

<div class="file-loading">
    <input class="input-ke-2" name="input-ke-2[]" type="file" multiple>
</div>


<script>
<!-- must load the font-awesome.css for this example -->
$(".input-ke-2").fileinput({
    theme: "explorer",
    uploadUrl: "/file-upload-batch/2",
    minFileCount: 2,
    maxFileCount: 5,
    overwriteInitial: false,
    previewFileIcon: '<i class="fas fa-file"></i>',
    initialPreviewAsData: true,
    uploadExtraData: {
        img_key: "1000",
        img_keywords: "happy, nature"
    },
    preferIconicPreview: true, // this will force thumbnails to display icons for following file extensions
         previewFileIconSettings: { // configure your icon file extensions
        'doc': '<i class="fa fa-file-word text-primary"></i>',
        'xls': '<i class="fa fa-file-excel text-success"></i>',
        'ppt': '<i class="fa fa-file-powerpoint text-danger"></i>',
        'pdf': '<i class="fa fa-file-pdf text-danger"></i>',
        'zip': '<i class="fa fa-file-archive text-muted"></i>',
        'htm': '<i class="fa fa-file-code text-info"></i>',
        'txt': '<i class="fa fa-file-text text-info"></i>',
        'mov': '<i class="fa fa-file-video text-warning"></i>',
        'mp3': '<i class="fa fa-file-audio text-warning"></i>',
        // note for these file types below no extension determination logic 
        // has been configured (the keys itself will be used as extensions)
        'jpg': '<i class="fa fa-file-image text-danger"></i>', 
        'gif': '<i class="fa fa-file-image text-muted"></i>', 
        'png': '<i class="fa fa-file-image text-primary"></i>'    
    },
    previewFileExtSettings: { // configure the logic for determining icon file extensions
        'doc': function(ext) {
            return ext.match(/(doc|docx)$/i);
        },
        'xls': function(ext) {
            return ext.match(/(xls|xlsx)$/i);
        },
        'ppt': function(ext) {
            return ext.match(/(ppt|pptx)$/i);
        },
        'zip': function(ext) {
            return ext.match(/(zip|rar|tar|gzip|gz|7z)$/i);
        },
        'htm': function(ext) {
            return ext.match(/(htm|html)$/i);
        },
        'txt': function(ext) {
            return ext.match(/(txt|ini|csv|java|php|js|css)$/i);
        },
        'mov': function(ext) {
            return ext.match(/(avi|mpg|mkv|mov|mp4|3gp|webm|wmv)$/i);
        },
        'mp3': function(ext) {
            return ext.match(/(mp3|wav)$/i);
        }
    }
});
</script>
</body>
</html>