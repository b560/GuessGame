import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { KeyValuePipe } from '@angular/common';
import { CommonModule } from '@angular/common';
import { GuessService } from './services/guesses.services';
import {Guess} from './guess';
import { FormsModule } from '@angular/forms';
import { AppConstants} from './constants/app.constants';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,KeyValuePipe,CommonModule,FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  gussedWord : string  = '';

  guess : Guess = new Guess("");
  guessGameResultList : string[] = [];
  numberOFGuesses : number = 0;
  isButtonClicked : boolean = false;
  firstInstruction: string = AppConstants.FIRST_INSTRUCTION;
  secondInstruction: string = AppConstants.SECOND_INSTRUCTION;
  thirdInstruction: string = AppConstants.THIRD_INSTRUCTION;
  fourthInstruction: string = AppConstants.FOURTH_INSTRUCTION;

  constructor(private  guessService: GuessService) {}

  guessInput(event:any) : any{
    this.gussedWord = event.target.value;   
    if(this.gussedWord.length < 5){
      this.isButtonClicked = false;
    }                                                  
    return this.gussedWord;
  }

  callGusseGameService(){
    this.numberOFGuesses++;
    if(this.numberOFGuesses >= 6){
      alert("Game Over");
    }
    this.guessService.callGuessData(this.gussedWord).subscribe({  
      next: x => this.guessGameResultList = x,  
      error: err => console.error('An error occurred :', err),  
      complete: () => console.log('There are no more action happen.')  
    })
    this.isButtonClicked = true;
  }

  messageResult(correctGuess : number):string{
    return correctGuess == 5 ? "Congratulations, you guess right" : "Sorry,you guess "+ correctGuess + " Letter right " + "and " + (5-correctGuess) + " Letter wrong. " + "Your guess is wrong, please try again!!!";
  }

}
