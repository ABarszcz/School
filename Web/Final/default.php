<?php
$page_title = 'Your Business';
require_once('header.php');

$pageID = $_GET['pageID'];

if(empty($pageID)) { $pageID = 1;}

require('db.php');

$sql = "SELECT * FROM pages WHERE pageID = :pageID;";
		
//Fill the params and execute
$cmd = $conn->prepare($sql);
$cmd->bindParam(':pageID', $pageID, PDO::PARAM_INT);
$cmd->execute();
$results = $cmd->fetchall();

//Save selects in $title and $content
foreach($results as $result) {
	$title = $result['pageTitle'];
	$content = $result['pageContent'];
} //end of loop

$conn=null;
?>

<!-- Show the title and content -->
<div class="jumbotron">
    <h2><?php echo $title ?></h2>
    <p><?php echo $content ?></p>
</div>


<?php require_once('footer.php'); ?>
