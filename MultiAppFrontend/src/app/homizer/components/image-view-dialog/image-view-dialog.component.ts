import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-image-view-dialog',
  standalone: false,

  templateUrl: './image-view-dialog.component.html',
  styleUrl: './image-view-dialog.component.css'
})
export class ImageViewDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<ImageViewDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { image: string }
  ) {
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

}
