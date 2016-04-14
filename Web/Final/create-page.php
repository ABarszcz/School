<?php
require_once('auth.php');

$page_title = 'Manage Page';
require_once('header.php');

$pageID = $_GET['pageID'];

//Check to see if there's a numeric pageID in the querystring
if ((!empty($pageID)) && (is_numeric($pageID))) {

	require('db.php');
	
	$sql = "SELECT * FROM pages WHERE pageID = :pageID;";
			
	//Fill the params and execute
	$cmd = $conn->prepare($sql);
	$cmd->bindParam(':pageID', $pageID, PDO::PARAM_INT);
	$cmd->execute();
	$results = $cmd->fetchall();
	
	//Save selects in $title and $content
	foreach($results as $result) {
		$pageTitle = $result['pageTitle'];
		$pageContent = $result['pageContent'];
	} //end of loop
	
	$conn=null;
} else {
	$pageTitle = '';
	$pageContent = '';
}
?>

<h1 class="text-center"><?php echo $page_title ?></h1>

<!-- Start of create page form -->
<form class="form-horizontal" method="post" action="save-page.php">
<!-- Group containing title label and field -->
<div class="form-group">
    <label for="title" class="col-sm-2 control-label">Page Title</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="title" name="title" required placeholder="Enter Page Title" value="<?php echo $pageTitle ?>"/>
    </div>
</div>
<!-- Group containing content label and field -->
<div class="form-group">
	<label for="content" class="col-sm-2 control-label">Page Content</label>
	<div class="col-sm-10">
		<textarea type="text" class="form-control" id="content" name="content" rows="6" required placeholder="Enter Page Content" ><?php echo $pageContent ?></textarea>
	</div>
</div>
<!-- Hidden page ID field -->
<input type="hidden" id="pageID" name="pageID" value="<?php echo $pageID ?>" />
<!-- Area for buttons -->
    <div align="center">
		<?php if (empty($pageID)) { ?>
		<input type="submit" value="Create Page" class="btn btn-primary btn-md" />
		<?php } else { ?>
		<input type="submit" value="Edit Page" class="btn btn-primary btn-md" />
		<?php } ?>
		<input type="reset" value="Clear Form" class="btn btn-primary btn-md" />
	</div>
</form>

<?php require_once('footer.php'); ?>
