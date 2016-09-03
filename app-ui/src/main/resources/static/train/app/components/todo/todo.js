/// <reference path="../../models.ts"/>
System.register(['@angular/core', '@angular/forms', '../../models', '@angular2-material/card', '@angular2-material/input', '@angular2-material/button', '@angular2-material/checkbox', '@angular2-material/progress-circle'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, forms_1, forms_2, models_1, card_1, input_1, button_1, checkbox_1, progress_circle_1;
    var Todo;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (forms_1_1) {
                forms_1 = forms_1_1;
                forms_2 = forms_1_1;
            },
            function (models_1_1) {
                models_1 = models_1_1;
            },
            function (card_1_1) {
                card_1 = card_1_1;
            },
            function (input_1_1) {
                input_1 = input_1_1;
            },
            function (button_1_1) {
                button_1 = button_1_1;
            },
            function (checkbox_1_1) {
                checkbox_1 = checkbox_1_1;
            },
            function (progress_circle_1_1) {
                progress_circle_1 = progress_circle_1_1;
            }],
        execute: function() {
            Todo = (function () {
                function Todo(fb) {
                    this.fb = fb;
                    this.todos = new Array();
                    this.todos.push(new models_1.TodoItem('Hello world', false));
                    this.buildForm();
                }
                Todo.prototype.ngOnInit = function () {
                    console.log('ngOnInit() called');
                };
                Todo.prototype.buildForm = function () {
                    this.newTodo = new forms_1.FormControl('', forms_2.Validators.required);
                    this.myForm = this.fb.group({
                        'newTodo': this.newTodo
                    });
                };
                Todo.prototype.removeTodo = function (item) {
                    this.todos.splice(this.todos.indexOf(item), 1);
                };
                Todo.prototype.onSubmit = function () {
                    if (this.myForm.valid) {
                        this.todos.push(new models_1.TodoItem(this.newTodo.value, false));
                        // How in hell do I reset this thing and prevent it from being validated?
                        // The only thing that works is rebuilding the whole form/&%¤#""
                        this.buildForm();
                    }
                };
                Todo.prototype.toggleAll = function (completed) {
                    this.todos.forEach(function (todo) {
                        todo.completed = completed;
                    });
                };
                Todo = __decorate([
                    core_1.Component({
                        selector: 'todo',
                        viewProviders: [forms_1.FormBuilder],
                        //templateUrl: './app/components/todo/todo.html',
                        template: '',
                        directives: [
                            forms_1.FORM_DIRECTIVES,
                            forms_1.REACTIVE_FORM_DIRECTIVES,
                            button_1.MdButton,
                            checkbox_1.MdCheckbox,
                            progress_circle_1.MdSpinner,
                            progress_circle_1.MdProgressCircle,
                            card_1.MdCard,
                            input_1.MdInput]
                    }), 
                    __metadata('design:paramtypes', [forms_1.FormBuilder])
                ], Todo);
                return Todo;
            }());
            exports_1("Todo", Todo);
        }
    }
});
//# sourceMappingURL=todo.js.map