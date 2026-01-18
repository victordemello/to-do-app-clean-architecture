import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Task, CreateTaskRequest, UpdateTaskRequest, ApiError } from '../models/task.model';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiUrl}/task`;

  getAllTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.baseUrl}/all`).pipe(
      catchError(this.handleError)
    );
  }

  getTaskById(id: string): Observable<Task> {
    return this.http.get<Task>(`${this.baseUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  createTask(request: CreateTaskRequest): Observable<Task> {
    return this.http.post<Task>(`${this.baseUrl}/create`, request).pipe(
      catchError(this.handleError)
    );
  }

  updateTask(id: string, request: UpdateTaskRequest): Observable<Task> {
    return this.http.put<Task>(`${this.baseUrl}/${id}`, request).pipe(
      catchError(this.handleError)
    );
  }

  deleteTask(id: string): Observable<Task> {
    return this.http.delete<Task>(`${this.baseUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    let apiError: ApiError;

    if (error.error && typeof error.error === 'object' && 'message' in error.error) {
      apiError = error.error as ApiError;
    } else {
      apiError = {
        status: error.status,
        message: error.message || 'Unknown error',
        timestamp: new Date().toISOString()
      };
    }

    return throwError(() => apiError);
  }
}
