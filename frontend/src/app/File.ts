export interface MediaFile {
  name: String;
  url: URL;
}

export interface MediaUser {
  id: String;
  media: Array<MediaFile>;
  password: String;
  role: String;
}
