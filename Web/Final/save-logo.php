<?php ob_start();
require_once('auth.php');

//Start the body because the header does not need to be called
echo'<html><body>';

//Create flag
$ok=true;

//Get file size
$size = $_FILES['upload']['size'];
//Validate size
if($size < 1000 || $size > 10000000) {
	echo'Invalid File Size<br />';
	$ok=false;
} //End of size validation

//Get extension
$type = $_FILES['upload']['type'];
//Validate type
if($type != "image/jpeg" && type != "image/jpg" && type != "image/png") {
	echo'Invalid File Type';
	$ok=false;
} //End of type validation

if($ok) {
	//Get temp location
	$tmp_name = $_FILES['upload']['tmp_name'];

	//Save logo
	move_uploaded_file($tmp_name, "logo.jpg");
	
	//Redirect to homepage
	header('location:default.php');
} //End of $ok

//Close the body and html tags
echo'</body></html>';
ob_flush();
?>