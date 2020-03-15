Create the picker to select single or multiple files filtered by file preference and convert from Uri to File

```
FILTER_VIDEO = "video/*"
FILTER_IMAGE = "image/*"
FILTER_AUDIO = "audio/*"
FILTER_TEXT = "text/*"
FILTER_ANY = "*/*"

FilePicker.filter(FILTER_IMAGE).with(this)
```