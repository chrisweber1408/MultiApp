
export class LoginData {
    public email: string;
    public password: string;

    constructor(username: string, password: string) {
        this.email = username;
        this.password = password;
    }

}