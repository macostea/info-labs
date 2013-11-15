$dirname=$args[0]

# Return a list of all the files and folders (including subdirectories)
Get-ChildItem -recurse $dirname


foreach ($file in Get-ChildItem -recurse $dirname -include *.txt)
{
    # For each text file in the folder
    $file # Print the name of the text file
    cat $file | group | sort Count -descending | Select-Object -First 1 # Print the maximum number of identical lines
}

foreach ($containername in dir $dirname | where {$_.PsIsContainer})
{
    # For each folder in the current folder
    $containername # Print the folder name
    @(dir -force $containername).Count # Count the number of elements in the folder
}