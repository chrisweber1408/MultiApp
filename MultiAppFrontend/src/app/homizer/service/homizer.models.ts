export class HomizerItemDto {
  public id: string;
  public name: string;
  public description?: string;
  public image?: string;
  public number?: number;
  public homizerStorageId?: string;
  public homizerStorageName?: string;

  constructor(name: string, description?: string, image?: string, number?: number, id?: string) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.image = image;
    this.number = number;
  }
}

export class HomizerStorageDto {
  public id: string;
  public name: string;
  public description: string;
  public image?: string;

  constructor(name: string, description?: string, image?: string, id?: string) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.image = image;
  }
}

export class UserInfosDto {
  public email: string;
  public itemCount: number;
  public storageCount: number;

  constructor(email: string, itemCount: number, storageCount: number) {
    this.email = email;
    this.itemCount = itemCount;
    this.storageCount = storageCount;
  }
}
