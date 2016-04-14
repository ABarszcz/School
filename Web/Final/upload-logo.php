<?php
require_once('auth.php');
$page_title='Upload Logo';
require_once('header.php');
?>

<h1 class="text-center"><?php echo $page_title?></h1>

<!-- Form -->
<form class="form-horizontal" method="post" action="save-logo.php" enctype="multipart/form-data">
<!-- Group containing upload label and field -->
<div class="form-group">
	<label for ="upload" class="col-sm-2 control-label">Upload Logo</label>
	<input type="file" id="upload" name="upload" required />
</div>
<!-- Button -->
<div class="col-sm-2">
	<input type="submit" value="Upload" class="btn btn-primary btn-md"/>
</div>
</form>

<?php require_once('footer.php'); ?>