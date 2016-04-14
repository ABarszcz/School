<?php ob_start();
require_once('auth.php');

//Start the body because the header does not need to be called
echo'<html><body>';

//Store the inputs into variables
$title = $_POST['title'];
$content = $_POST['content'];
$pageID = $_POST['pageID'];
//Flag to determine if code can run
$ok = true;

//Make sure none of the fields are empty
if (empty($title)) {
    echo 'Page title is required<br />';
    $ok = false;
} if (empty($content)) {
    echo 'Page content is required<br />';
    $ok = false;
} //End of empty construct

if ($ok) {
	require('db.php');
	//Try/Catch error handler
	try {
	//If we are editing an existing page, use update
	if(!empty($pageID)) {
		echo $pageID;
		$sql = "UPDATE pages SET pageTitle=:title, pageContent=:content
		WHERE pageID=:pageID";
		
		//Fill the params and execute
		$cmd = $conn->prepare($sql);
		$cmd->bindParam(':title', $title, PDO::PARAM_STR, 30);
		$cmd->bindParam(':content', $content, PDO::PARAM_STR, 1500);
		$cmd->bindParam(':pageID', $pageID, PDO::PARAM_INT);
		$cmd->execute();
		
		//Redirect to the new page
		header('location:default.php?pageID=' . $pageID);
	} //End of existing page
	//If we are creating a new page, use INSERT
	else {
		$sql = "INSERT INTO pages (pageTitle, pageContent) VALUES (:title, :content)";

		//Fill the params and execute
		$cmd = $conn->prepare($sql);
		$cmd->bindParam(':title', $title, PDO::PARAM_STR, 30);
		$cmd->bindParam(':content', $content, PDO::PARAM_STR, 1500);
		$cmd->execute();
		
		header('location:default.php?pageID=' . $pageID);
	} //End of else if
	
	} catch (Exception $e) {
		//Email error to myself
		require_once('mail.php');
		//Redirect user to the error page
		header('location:error.php');
	} //End of try/catch
	$conn=null;
} //End of checking flag

//Close the body and html tags
echo'</body></html>';
ob_flush();
?>