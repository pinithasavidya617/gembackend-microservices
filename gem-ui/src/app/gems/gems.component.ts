import { Component, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../service/auth.service';
import { CommonModule } from '@angular/common';
import { CurrencyPipe } from '@angular/common';



interface Gem {
  id: number;
  name: string;
  type: string;
  caratWeight: number;
  pricePerCarat: number;
}

@Component({
  selector: 'app-gems',
  standalone: true,
  imports: [CommonModule, CurrencyPipe],
  templateUrl: './gems.component.html',
  styleUrl: './gems.component.scss'
})
export class GemsComponent {
   auth = inject(AuthService);
   private http = inject(HttpClient);

   gems = signal<Gem[]>([]);
   loading = signal<boolean>(false);
   error = signal<string | null>(null);

   fetchGems(){

    this.loading.set(true);
    this.error.set(null);

    this.http.get<Gem[]>('http://localhost:8088/api/v1/gems').subscribe({
      next: (gems) => {
        this.gems.set(gems);
        this.loading.set(false);
      },
      error: (err) => {
        this.error.set('Failed to fetch gems');
        this.loading.set(false);
      }
    });
   }


}
