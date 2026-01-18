import { Component, output } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [ButtonModule, RippleModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  onNewTask = output<void>();

  handleNewTask(): void {
    this.onNewTask.emit();
  }
}
