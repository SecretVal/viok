{
  description = "An example minecraft fabric mod";
  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
  };

  outputs = {nixpkgs, ...}: let
    pkgs = nixpkgs.legacyPackages.x86_64-linux;
    libs = with pkgs; [
      flite
      libpulseaudio
      libGL
      glfw-wayland-minecraft
      openal
      stdenv.cc.cc.lib
    ];
  in {
    devShell.x86_64-linux = pkgs.mkShell {
      packages = [
        pkgs.jetbrains.jdk
      ];
      buildInputs = libs;
      LD_LIBRARY_PATH = pkgs.lib.makeLibraryPath libs;
    };
  };
}
