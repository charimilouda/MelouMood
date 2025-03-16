import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecettesListComponent } from './recettes-list.component';

describe('RecettesListComponent', () => {
  let component: RecettesListComponent;
  let fixture: ComponentFixture<RecettesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RecettesListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecettesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
