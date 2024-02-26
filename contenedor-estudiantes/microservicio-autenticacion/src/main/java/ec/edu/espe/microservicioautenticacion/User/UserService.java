package ec.edu.espe.microservicioautenticacion.User;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
private final UserRepository userRepository; 

    @Transactional
    public UserResponse updateUser(UserRequest userRequest) {
       
        User user = User.builder()
        .id(userRequest.id)
        .nombres(userRequest.getNombres())
        .apellidos(userRequest.getApellidos())
        .role(userRequest.getRole())
        .build();
        
        userRepository.updateUser(user.id, user.nombres, user.apellidos);

        return new UserResponse("El usuario se registró satisfactoriamente");
    }

    public UserDTO getUser(String id) {
        User user= userRepository.findById(id).orElse(null);
       
        if (user!=null)
        {
            UserDTO userDTO = UserDTO.builder()
            .id(user.id)
            .username(user.username)
            .nombres(user.nombres)
            .apellidos(user.apellidos)
            .role(user.role)
            .build();
            return userDTO;
        }
        return null;
    }

    public UserDTO findByUserName(String usr) {
        User user= userRepository.findByUsername(usr).orElse(null);
       
        if (user!=null)
        {
            UserDTO userDTO = UserDTO.builder()
            .id(user.id)
            .username(user.username)
            .nombres(user.nombres)
            .apellidos(user.apellidos)
            .role(user.role)
            .build();
            return userDTO;
        }
        return null;
    }
}
