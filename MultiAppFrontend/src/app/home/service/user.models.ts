
export class LoginData {
    
    public email: string;
    public password: string;

    constructor(username: string, password: string) {
        this.email = username;
        this.password = password;
    }

}

export class RegisterData {

    public email: string;
    public password: string;
    public passwordRepeat: string;

    constructor(username: string, password: string, passwordRepeat: string) {
        this.email = username;
        this.password = password;
        this.passwordRepeat = passwordRepeat;
    }

}